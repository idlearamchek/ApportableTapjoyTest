//
//  TapjoyWrapper.m
//
//  Created by Ghislain Bernier on 02/04/14.
//  Copyright (c) 2014 All rights reserved.
//

#import "TapjoyWrapper.h"

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wincomplete-implementation"
@implementation TapjoyWrapper

+ (void)initializeJava
{
    [super initializeJava];
    
    [TapjoyWrapper registerConstructorWithSelector:@selector(initWithActivity:)
                                                  arguments:@"android/app/Activity", nil];
    
    
    [TapjoyWrapper registerInstanceMethod:@"setupTapjoy"
                                          selector:@selector(setupTapjoy)];
}

+ (NSString *)className
{
    return @"com.tapjoy.TapjoyWrapper";
}

@end
#pragma clang diagnostic pop
